package com.example.desafio_android.data.dto

data class RepositoryPullRequest(
    val url: String?,
    val id: Int?,
    val node_id: String?,
    val html_url: String?,
    val diff_url: String?,
    val patch_url: String?,
    val issue_url: String?,
    val number: Int?,
    val state: String?,
    val locked: Boolean?,
    val title: String?,
    val user: User?,
    val body: String?,
    val created_at: String?,
    val updated_at: String?,
    val closed_at: String?,
    val merged_at: String?,
    val merge_commit_sha: String?,
    val assignee: User??,
    val assignees: List<User>?,
    val requested_reviewers: List<User>?,
    val requested_teams: List<Team>?,
    val labels: List<Label>?,
    val milestone: Milestone?,
    val draft: Boolean?,
    val commits_url: String?,
    val review_comments_url: String?,
    val review_comment_url: String?,
    val comments_url: String?,
    val statuses_url: String?,
    val head: Branch?,
    val base: Branch?,
    val _links: Links?,
    val author_association: String?,
    val auto_merge: Boolean?,
    val active_lock_reason: String?
)

data class User(
    val login: String?,
    val id: Int?,
    val node_id: String?,
    val avatar_url: String?,
    val gravatar_id: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organizations_url: String?,
    val repos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: Boolean?
)

data class Label(
    val id: Long?,
    val node_id: String?,
    val url: String?,
    val name: String?,
    val color: String?,
    val default: Boolean?,
    val description: String?
)

data class Links(
    val self: Link?,
    val html: Link?,
    val issue: Link?,
    val comments: Link?,
    val review_comments: Link?,
    val review_comment: Link?,
    val commits: Link?,
    val statuses: Link?
)

data class Link(
    val href: String?
)

data class Branch(
    val label: String?,
    val ref: String?,
    val sha: String?,
    val user: User?,
    val repo: Repository?
)

data class Repository(
    val id: Long?,
    val node_id: String?,
    val name: String?,
    val full_name: String?,
    val private: Boolean?,
    val owner: User?,
    val html_url: String?,
    val description: String?,
    val fork: Boolean?,
    val url: String?,
    val forks_url: String?,
    val keys_url: String?,
    val collaborators_url: String?,
    val teams_url: String?,
    val hooks_url: String?,
    val issue_events_url: String?,
    val events_url: String?,
    val assignees_url: String?,
    val branches_url: String?,
    val tags_url: String?,
    val blobs_url: String?,
    val git_tags_url: String?,
    val git_refs_url: String?,
    val trees_url: String?,
    val statuses_url: String?,
    val languages_url: String?,
    val stargazers_url: String?,
    val contributors_url: String?,
    val subscribers_url: String?,
    val subscription_url: String?,
    val commits_url: String?,
    val git_commits_url: String?,
    val comments_url: String?,
    val issue_comment_url: String?,
    val contents_url: String?,
    val compare_url: String?,
    val merges_url: String?,
    val archive_url: String?,
    val downloads_url: String?,
    val issues_url: String?,
    val pulls_url: String?,
    val milestones_url: String?,
    val notifications_url: String?,
    val labels_url: String?,
    val releases_url: String?,
    val deployments_url: String?,
    val created_at: String?,
    val updated_at: String?,
    val pushed_at: String?,
    val git_url: String?,
    val ssh_url: String?,
    val clone_url: String?,
    val svn_url: String?,
    val homepage: String?,
    val size: Long?,
    val stargazers_count: Long?,
    val watchers_count: Long?,
    val language: String?,
    val has_issues: Boolean?,
    val has_projects: Boolean?,
    val has_downloads: Boolean?,
    val has_wiki: Boolean?,
    val has_pages: Boolean?,
    val has_discussions: Boolean?,
    val forks_count: Long?,
    val mirror_url: String?,
    val archived: Boolean?,
    val disabled: Boolean?,
    val open_issues_count: Long?,
    val license: License?,
    val allow_forking: Boolean?,
    val is_template: Boolean?,
    val web_commit_signoff_required: Boolean?,
    val topics: List<String>?,
    val visibility: String?,
    val forks: Long?,
    val open_issues: Long?,
    val watchers: Long?,
    val default_branch: String?
)

data class License(
    val key: String?,
    val name: String?,
    val spdx_id: String?,
    val url: String?,
    val node_id: String?
)

data class Milestone(
    val url: String?,
    val html_url: String?,
    val labels_url: String?,
    val id: Int?,
    val node_id: String?,
    val number: Int?,
    val state: String?,
    val title: String?,
    val description: String?,
    val creator: User?,
    val open_issues: Int?,
    val closed_issues: Int?,
    val created_at: String?,
    val updated_at: String?,
    val closed_at: String?,
    val due_on: String?
)

data class Team(
    val id: Int?,
    val node_id: String?,
    val url: String?,
    val html_url: String?,
    val name: String?,
    val slug: String?,
    val description: String?,
    val privacy: String?,
    val notification_setting: String?,
    val permission: String?,
    val members_url: String?,
    val repositories_url: String?
)